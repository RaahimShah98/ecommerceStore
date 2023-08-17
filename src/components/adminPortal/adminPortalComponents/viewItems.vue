<template>
    <adminHeader></adminHeader>

    <div id="adminPortalBody">
        <navBar></navBar>
        <div id="adminPortalBodyRight">


            <div class="viewDiv" v-for="items in item" :key="items.id">
                <div id="ItemImageDiv">
                    <img class="itemImage" :src="items.imgURL">
                </div>

                <div id="ItemDetailDiv">

                    <p><b>ItemID:</b> {{ items.id }}</p>
                    <p><b>ItemName:</b> {{ items.name }}</p>
                    <p><b>Category:</b> {{ items.category }}</p>
                    <p><b>Description:</b> {{ items.description }}</p>
                    <p><b>Quantity:</b> {{ items.quantity }}</p>
                    <p><b>Price:</b> {{ items.price }}</p>
                    <button class="adminEditItemBTN" v-bind:id="items.id" v-on:click="editItem(items.id, items.name,items.price,items.category, items.description, items.quantity)">Edit Item</button>
                    <button class="adminRemoveItemBTN" v-bind:id="items.id" v-on:click="deleteItem(items.id)">Remove
                        Item</button>
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
            item: {}
        }
    }, methods: {
        deleteItem(id) {

            // console.log(this.data.users.id);

            axios.post("http://localhost:8080/manageItems/deleteItem", id)
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
        }, editItem(id, name,price, category, description, quantity) {

            this.$router.push({ path: "/adminPortal/AddItemComponent", query: { itemId:id, itemName:name, itemPrice:price , itemCategory:category, itemDesc : description,itemQuantity:quantity ,btnChange: "update" } })
        },
    },
    created() {
        console.log("hello")
        axios.get("http://localhost:8080/manageItems/viewItems")
            .then(response => {
                // Handle the response data
                console.log(response)
                this.item = response.data;
                console.log(this.item)


            })
            .catch(error => {
                // Handle the error
                console.log("error")
                console.error(error);
            });
    }

}
</script>

<style scoped>
.itemImage {
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

#ItemImageDiv {
    width: 30%;
    padding: 20px;

    background-color: grey;
    /* height: 100%; */
}

#ItemDetailDiv {
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