<template>
    <div id="customerItemsComponent">
        <div class="itemBox" v-for="item in items" :key="item.id" v-on:click="viewItem(item.id, item.name, item.price, item.imgURL, item.description, quantity)">
            <img class="itemImage" :src="item.imgURL" alt="">
            <h1 class="itemName">{{ item.name }}</h1>
            <p class="itemPrice"><b>Price: </b>{{ item.price }}</p>


       
        </div>


    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            items: {},
            viewItems: [],
            quantity: '1'

        }
    },
    methods: {
 
        viewItem(itemId, itemName, itemPrice, itemImage, itemDescription, itemQuantity) {
            event.preventDefault();
            console.log(itemName)
            const itemToCart = { id: itemId, Name: itemName, Price: itemPrice, image: itemImage, description: itemDescription, quantity: itemQuantity }
            this.viewItems = itemToCart
            
            localStorage.setItem('viewItem', JSON.stringify(this.viewItems))
             this.$router.push(`/viewItem/${itemId}`)


        },
        fetchCartData() {
            const existingCartData = JSON.parse(localStorage.getItem('cartItem'));
            if (existingCartData && Array.isArray(existingCartData)) {
                this.viewItems = existingCartData;

            }
        },
        getCategoryId() {
            return localStorage.getItem("categoryId")
        }
    },
  
    mounted() {
        this.fetchCartData();
    
        const getId = this.getCategoryId();
        console.log(getId)

        if (getId) {
            console.log("inside get")
            axios.get("http://localhost:8080/manageItems/getItemByCategory", {
                params: {
                    categoryID: getId,
                }
            }).then(response => {
                // console.log(response)
                this.items = response.data
                console.log(this.items)
            }).catch(error => {
                console.log(error)
            })
        }
        else {
            console.log("not inside get")
            axios.get("http://localhost:8080/manageItems/viewItems").then(response => {
                // console.log(response)
                this.items = response.data
                console.log(this.items)
            }).catch(error => {
                console.log(error)
            })
        }
    }
}

</script>

<style scoped>
#customerItemsComponent {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    margin: 20px;
}

.itemBox {
    background-color: white;
    display: inline-block;
    
    margin-top: 20px;
    
    width: 30%;
    border: 1px solid black;
    

}

.itemBox:hover{
    cursor: pointer;
    transform: scale(1.01);
    background-color: rgb(13, 13, 72);
    color: white;
}
.itemName {
    background-color: rgb(13, 13, 72);
    color: white;
    font-size: 17px;
}

.itemPrice {
    font-size: 15px;

}

.itemImage {
    width: 100%;
    height: 290px;
}
</style>
































