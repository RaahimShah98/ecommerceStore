<template>
    <adminHeader></adminHeader>

    <div id="adminPortalBody">
        <navBar></navBar>
        <div id="adminPortalBodyRight">

            <table id="categoryTable">
                <tr class="viewTableRow">
                    <td class="viewTableData">ID</td>
                    <td class="viewTableData">Name</td>
                    <td class="viewTableData">Remove</td>

                </tr>

                <tr class="viewTableRow" v-for="categ in Category" :key="categ.id">

                    <td class="viewTableData">{{ categ.id }}</td>
                    <td class="viewTableData">{{ categ.name }}</td>
                    <td class="viewTableData"><button class="adminRemoveItemBTN" v-bind:id="categ.id" v-on:click="deleteCategory(categ.id)">Remove Category</button></td>
                </tr>

            </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios';


import navBar from './adminNavBar.vue'
import adminHeader from './adminPortalHeader.vue'

export default {
    components: {
        navBar,
        adminHeader
    },
    data() {
        return {
            Category: {

                "id": "",
                "name": ""

            }

        }
    },
    methods: {
        deleteCategory(id) {
            // console.log(this.data.users.id);
            console.log(id)
            axios.post("http://localhost:8080/manageCategory/deleteCategory", id)
                .then(response => {
                    // Handle the response data
                    console.log(typeof (response.data))
                    console.log(response)
                    console.log("Data Sent")
                    // console.log(this.users.users[0]);
                    // console.log(response.data);
                })
                .catch(error => {
                    // Handle the error
                    console.error(error);
                });
        }
    },
    created() {

        axios.get("http://localhost:8080/manageCategory/viewCategory")
            .then(response => {
                // Handle the response data

                // console.log(response.data)
                this.Category = response.data;
                // console.log(this.users.users[0]);
                // console.log(response.data);
            })
            .catch(error => {
                // Handle the error
                console.error(error);
            });
    }
}


</script>

<style scoped>
#categoryTable{
    width: 100%;
}
</style>