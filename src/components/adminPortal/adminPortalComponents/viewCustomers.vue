<template>
    <adminHeader></adminHeader>

    <div id="adminPortalBody">
        <navBar></navBar>
        <div id="adminPortalBodyRight">


            <div class="viewDiv" v-for="user in users" :key="user.id">

                <div id="CustomerImageDiv">
                    <img id="CustomerImage" v-bind:src="user.image">
                </div>

                <div id="CustoemerDetailDiv">
                    <h1>{{ user.name }}</h1>
                    <p><b>UserID:</b> {{ user.id }}</p>
                    <p><b>Email:</b> {{ user.email }}</p>
                    <p><b>Address:</b> {{ user.address }}</p>
                    <p><b>PhoneNo:</b> {{ user.phoneNo }}</p>
                    <p><b>Status:</b> {{ user.status }}</p>
                    <button class="adminRemoveItemBTN" v-bind:id="user.id" v-on:click="deleteUser(user.id)">Remove
                        User</button>
                    <button class="adminEditItemBTN" v-bind:id="user.id"
                        v-on:click="editUser(user.id, user.name, user.email, user.phoneNo, user.password, user.address, user.status , user.image)">Edit
                        User</button>
                </div>

            </div>


        </div>
    </div>
</template>


<script>
import axios from 'axios'

import navBar from './adminNavBar.vue'
import adminHeader from './adminPortalHeader.vue'

export default {
    components: {
        navBar,
        adminHeader
    },
    data() {
        return {
            users: {}
        }
    },
    methods: {
        deleteUser(id) {
            // console.log(this.data.users.id);

            axios.post("http://localhost:8080/manageUsers/deleteUser", id)
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
        },
        editUser(id, name, email, phoneNo, password, address, status , image) {
            console.log(id, name, email, address)
            this.$router.push({ path: "/adminPortal/addUser", query: { userId: id, userName: name, userEmail: email, userPhone: phoneNo, userPassword: password, userAddress: address, userStatus: status, userImage:image, btnChange: "update" } })
        },

    },

    mounted() {
        axios.get("http://localhost:8080/manageUsers/viewUser")
            .then(response => {
                // Handle the response data
                // console.log(typeof(response.data))
                // console.log(response.data)
                this.users = response.data;
                console.log(this.users)
            })
            .catch(error => {
                // Handle the error
                console.error(error);
            });
    }
}


</script>

<style scoped>
#CustomerImage {
    width: 100%;
    height: 100%;
}

.viewDiv {
    display: flex;
    background-color: green;
    margin-bottom: 10px;
    width: 100%;
    border: 2px solid black;
    /* padding: 20px 0px; */
}

#CustomerImageDiv {
    width: 30%;
    padding: 20px;

    background-color: grey;
    /* height: 100%; */
}

#CustoemerDetailDiv {
    width: 70%;
    display: inline-block;
    text-align: center;
    background-color: white;
    padding-top: 20px;
    padding-bottom: 20px;
}

#CustoemerDetailDiv p {
    margin-top: 10px;
    margin-bottom: 10px;
    font-size: 20px;
}</style>