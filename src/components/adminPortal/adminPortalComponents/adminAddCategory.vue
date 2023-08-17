<template>
    <adminHeader></adminHeader>

    <div id="adminPortalBody">
        <navBar></navBar>
        <div id="adminPortalBodyRight">

            <form action="">
                <input type="text" class="addCategoryField" id="addCategoryField" placeholder="Category Id"
                    v-model="Category.id">
                <input type="text" class="addCategoryField" id="addCategoryNameField" placeholder="Category Name"
                    v-model="Category.name">
                <button class="addCategoryBtn" id="addCategoryBtn" v-on:click="addCategory">Add Category</button>
            </form>
            <p id="successfullMsg">Item added successfully</p>
            <p id="failMsg">Item cannot be added same id already exists</p>
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
        addCategory() {
            event.preventDefault();
            let sucess = document.getElementById("successfullMsg")
            let fail = document.getElementById("failMsg")

            sucess.style.display = 'none'
            fail.style.display = 'none'
            axios.post("http://localhost:8080/manageCategory/addCategory", this.Category)
                .then(response => {
                    console.log(response.data)
                    sucess.style.display = 'block'
                    console.log("Data added succesfully");
                }).catch(error => {
                    fail.style.display = 'block'
                    console.log(error)
                })

        }
    }
}
</script>

<style scoped>

#failMsg {
    color: rgb(219, 17, 17);
    font-size: 18px;
    display: none;
}

 #successfullMsg{
    color: green;
    font-size: 18px;
    display: none;
}
.addCategoryField {
    width: 80%;
    padding: 10px 5px;
    border-radius: 5px;
    border: 1px solid black;
    margin:10px;
}
.addCategoryBtn{
width: 80%;
padding: 7px;
font-size: 18px;
color: white;
border: none;
border-radius: 5px;
background-color: green;
}
.addCategoryBtn:hover{
    cursor: pointer;
    transform: scale(1.01);
}
</style>