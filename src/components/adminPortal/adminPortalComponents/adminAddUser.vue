
<template>
    <adminHeader></adminHeader>

    <div id="adminPortalBody">
        <navBar></navBar>
        <div id="adminPortalBodyRight">

            <form>
                <!-- <form> -->

                <input type="text" name="" class="addItemField" placeholder="Name" v-model="user.Name">
                <input type="email" name="" class="addItemField" placeholder="email" v-model="user.email">
                <input type="text" name="" class="addItemField" placeholder="Phone No" v-model="user.phoneNo">
                <input type="password" name="" class="addItemField" placeholder="password" v-model="user.password">
                <input type="text" name="" class="addItemField" placeholder="address" v-model="user.address">

                <br>

                <select name="" id="" v-model="user.status">
                    <option value="Manager">Manager</option>
                    <option value="Worker">Worker</option>
                    <option value="Driver">Driver</option>
                    <option value="Cook">Cook</option>
                </select>
                <input type="file" class="addItemField" name="" id="" @change="setImgURL">

                <button id="addItemBtn" v-on:click="addUser" v-if="btnChange === 'add'">Add User</button>
    
                <button id="updateItemBtn" v-on:click="updateUser" v-if="btnChange === 'update'">Update User</button>

            </form>

        </div>
    </div>
</template>

<script>
import axios from 'axios';
// import { createCanvas, loadImage } from 'canvas';

import navBar from './adminNavBar.vue'
import adminHeader from './adminPortalHeader.vue'


export default {
    components: {
        navBar,
        adminHeader
    },
    data() {
        return {
            user: {
                "id": "",
                "Name": "",
                "email": "",
                "phoneNo": "",
                "password": "",
                "address": "",
                "status": "",
                "profilePicture": ""
            },
            "btnChange": "add",



        }
    },
    methods: {
    
        addUser() {
            event.preventDefault();

            axios.post("http://localhost:8080/manageUsers/addUser", this.user)
                .then(response => {
                    // Handle the response data

                    console.log(response.data)

                })
                .catch(error => {
                    // Handle the error
                    console.error(error);
                })
        },
        updateUser() {
            event.preventDefault();

            axios.post("http://localhost:8080/manageUsers/updateUser", this.user)
                .then(response => {
                    // Handle the response data

                    console.log(response.data)

                })
                .catch(error => {
                    // Handle the error
                    console.error(error);
                })
        },
        setImgURL() {

            const file = event.target.files[0];

            if (file) {

                console.log("hello")
                const reader = new FileReader();

                reader.onload = () => {
                    const imgSrc = reader;
                    this.user.profilePicture = imgSrc.result;
                    console.log(this.user.profilePicture);

                };

                reader.readAsDataURL(file);
            }

        },
    },
     beforeRouteEnter(to, from, next) {
            // Check if query parameters are present when entering the route
            console.log("hello")
            if (Object.keys(to.query).length > 0) {
                // Set the dataVariable based on the query parameters
                next(vm => {
                    vm.user.id = to.query.id;
                    vm.user.Name = to.query.userName;
                    vm.user.email = to.query.userEmail;
                    vm.user.phoneNo = to.query.userPhone;
                    vm.user.password = to.query.userPassword;
                    vm.user.status = to.query.userStatus;
                    vm.user.address = to.query.userAddress;
                    vm.user.profilePicture = to.query.userImage;
                    vm.btnChange = to.query.btnChange;

                    

                });
            } else {
                next();
            }
        },
        beforeRouteUpdate(to, from, next) {
            // Check if query parameters are present when updating the route
            if (Object.keys(to.query).length > 0) {
                // Set the dataVariable based on the query parameters
                this.user.id = "";
                this.user.Name = "";
                this.user.email = "";
                this.user.phoneNo = "";
                this.user.password = "";
                this.user.address = "";
                this.user.status = "";
                this.user.profilePicture = "";
                this.btnChange = "add";


            }
            next();

        },

}

</script>

<style scoped>
.addItemField {
    margin: 5px;
    width: 60%;
    padding: 5px;

}


#updateItemBtn {
    display: inline-block;
    padding: 12px 24px;
    font-size: 16px;
    color: #fff;
    background-color: #007bff;
    /* Blue color, you can change it to any other color you prefer */
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.2s;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

#addItemBtn {
    margin: 20px;
    display: inline-block;
    padding: 12px 24px;
    font-size: 16px;
    color: #fff;
    background-color: #168200;
    /* Blue color, you can change it to any other color you prefer */
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.2s;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

#addItemBtn:hover , #updateItemBtn {
    background-color: #0056b3;
    /* Darker shade of blue on hover */
    transform: translateY(-2px);
}
</style>