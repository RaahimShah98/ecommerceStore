<template>
    <adminHeader></adminHeader>

    <div id="adminPortalBody">
        <navBar></navBar>
        <div id="adminPortalBodyRight">

            <table id="categoryTable">
                <tr class="viewTableRow">
                    <td class="viewTableData">ID</td>
                    <td class="viewTableData">Name</td>
                    <td class="viewTableData">Email</td>
                    <td class="viewTableData">PhoneNo</td>
                    <td class="viewTableData">View Orders</td>
                </tr>

                <tr class="viewTableRow" v-for="cust in customers" :key="cust.id">

                    <td class="viewTableData">{{ cust.id }}</td>
                    <td class="viewTableData">{{ cust.name }}</td>
                    <td class="viewTableData">{{ cust.email }}</td>
                    <td class="viewTableData">{{ cust.phoneNo }}</td>
                  
                    <td class="viewTableData"><button class="adminRemoveItemBTN" v-on:click="viewOrder(cust.id)">View Orders</button></td>
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
            customers: {}
        }
    },methods:{
        viewOrder(custID){
            console.log(custID)

        }
    },
    created() {

        axios.get("http://localhost:8080/manageCustomer/viewCustomers")
            .then(response => {
           
                // console.log(response.data)
            //    console.log(response.data)
                this.customers = response.data;
                
                console.log(this.customers)

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

.adminRemoveItemBTN{
    background-color: rgb(0, 132, 255);
}
</style>