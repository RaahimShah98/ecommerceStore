<template>
     <div class="item" v-for="item in items" :key="item.id">
                <div class="itemImage">
                    <img class="itemImage" :src="item.image">
                </div>

                <div class="itemDetails">
                    <p class="itemName"><b>Item: </b>{{ item.Name }}</p>
                    <p class="itemPrice"><b>Price: </b>{{ item.Price }}</p>
                    <p class="itemQuantity"><b>Quantity: </b>{{ item.quantity }}</p>
                    <button v-on:click="removeFromLocalStorage(item.uId, item.id, item.Price, item.quantity)"
                        class="removeItemBtn">Remove Item</button>
                </div>

            </div>
</template>

<script>
    export default {
        data(){
            return{
                items:[]
            }
        },
        props:{
            'getItems' :{}
        },
        
    created(){
        this.items = this.getItems
        // console.log(this.getItems)
        // console.log(this.items)
    }            
        
        
    }
</script>

<style scoped>

.item {
    display: flex;
    background-color: rgb(255, 255, 255);
    width: 100%;
    height: 200px;
    padding-left: 10px;
    border-bottom: 1px solid black;

    align-items: center;

}

.itemImage {
    width: 200px;
    height: 95%;
}

.itemDetails {
    width: 70%;
}

.removeItemBtn {
    background-color: red;
    border: none;
    width: 150px;
    margin-top: 40px;
    height: 30px;
    font-size: 20px;
    color: white;
    border-radius: 4px;
}

.removeItemBtn:hover {
    cursor: pointer;
}

</style>