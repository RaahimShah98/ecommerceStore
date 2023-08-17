
<template>
    <adminHeader></adminHeader>

    <div id="adminPortalBody">
        <navBar></navBar>
        <div id="adminPortalBodyRight">

            <form>
                <input type="text" name="" id="addItemName" class="addItemField" placeholder="Id" v-model="item.id">
                <input type="text" name="" id="addItemName" class="addItemField" placeholder="Name" v-model="item.Name">
                <input type="text" name="" id="addItemName" class="addItemField" placeholder="Price" v-model="item.Price">
                <input type="text" name="" id="addItemName" class="addItemField" placeholder="Description"
                    v-model="item.Description">
                <input type="text" name="" id="addItemName" class="addItemField" placeholder="Quantity"
                    v-model="item.Quantity">
                <br>

                <select name="" id="" class="addItemField" v-model="item.Category">
                    <option v-bind:id="categ.id" v-for="categ in Categories" :key="categ.id">{{ categ.id }}</option>
                </select>
                <input type="file" class="addItemField" name="" id="" @change="setImgURL">
                <button id="addItemBtn" v-on:click="addItem" v-if="btnChange === 'add'">Add Item</button>
                <!-- <button id="addItemBtn" v-on:click="addItem">Add User</button> -->
                <button id="updateItemBtn" v-on:click="updateUser" v-if="btnChange === 'update'">Update Item</button>
            </form>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
// import { createCanvas, loadImage } from 'canvas';
import md5 from 'md5';

import navBar from './adminNavBar.vue'
import adminHeader from './adminPortalHeader.vue'


export default {
    components: {
        navBar,
        adminHeader
    },
    data() {
        return {
            item: {
                "id": "",
                "Name": "",
                "Price": "",
                "Description": "",
                "Quantity": "",
                "Category": "",
                "imgSrc": ""
            },
            Categories: {

            },
            "btnChange": "add",
        }
    },
    methods: {
        addItem() {
            event.preventDefault();

            axios.post("http://localhost:8080/manageItems/addItem", this.item)
                .then(response => {
                    // Handle the response data

                    console.log(response.data)
                    this.Categories = response.data;
                    // console.log(this.users.users[0]);
                    // console.log(response.data);
                })
                .catch(error => {
                    // Handle the error
                    console.error(error);
                })
            // this.$router.push('/adminPortal/viewItemComponent')
        },
        updateUser() {
            event.preventDefault();

            axios.post("http://localhost:8080//manageItems/updateItem", this.image)
                .then(response => {
                    // Handle the response data

                    console.log(response.data)

                })
                .catch(error => {
                    // Handle the error
                    console.error(error);
                })
            // this.$router.push('/adminPortal/viewItemComponent')
        },
        setImgURL() {
            event.preventDefault()
            const file = event.target.files[0];

            if (file) {

                console.log("hello")
                const reader = new FileReader();

                reader.readAsDataURL(file);

                reader.onload = () => {
                    const imgSrc = reader;

                    this.image.imgSrc = imgSrc.result
                    console.log(this.image.imgSrc)

                    // Do something with the image source (e.g., assign it to a data property)
                    // this.image.imgSrc = "src/assets/IMG_0277.JPG" +  imgSrc;
                };
            }


        },
        generateShortAlias(dataURL) {
            const hash = md5(dataURL); // Generate the MD5 hash of the dataURL
            return hash.substring(0, 8); // Return the first 8 characters of the hash as the short alias
        },

    },
    beforeRouteEnter(to, from, next) {
        // Check if query parameters are present when entering the route
        console.log("hello")
        if (Object.keys(to.query).length > 0) {

            next(vm => {
                vm.image.id = to.query.itemId;
                vm.image.Name = to.query.itemName;
                vm.image.Price = to.query.itemPrice;
                vm.image.Description = to.query.itemDesc;
                vm.image.Quantity = to.query.itemQuantity;
                vm.image.Category = to.query.itemCategory;
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
            this.image.id = "";
            this.image.Name = "";
            this.image.Price = "";
            this.image.Description = "";
            this.image.Quantity = "";
            this.image.Category = "";
            this.image.imgSrc = "";
            this.btnChange = "add";



        }
        next();
    },
    created() {
        axios.get("http://localhost:8080/manageCategory/viewCategory")
            .then(response => {
                // Handle the response data

                // console.log(response.data)
                this.Categories = response.data;
                // console.log(this.Categories)
            })
            .catch(error => {
                // Handle the error
                console.error(error);
            });
    }

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

#addItemBtn:hover,
#updateItemBtn:hover {
    background-color: #0056b3;
    /* Darker shade of blue on hover */
    transform: translateY(-2px);
}</style>