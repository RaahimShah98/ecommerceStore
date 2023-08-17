<template>
    <div id="checkOutDiv">

        <div id="seeItem">
            <div class="item" v-for="item in getItems" :key="item.id">
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

        </div>
        <div id="totalPrice">
            <b>SubTotal:</b> {{ totalPrice }}
            <b>GST({{ gst }}):</b> {{ gst }}
            <b>TOTAL:</b> {{ totalPricewithTax }}
            <div id="confirmOrder"><button id="confirmOrderBtn" v-on:click="toOrderConfirm">Proceed To CheckOut</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            getItems: [],
            totalPrice: 0,
            gst: 0,
            totalPricewithTax: 0
        }
    },
    methods: {
        toOrderConfirm() {
            console.log("called")
            this.$router.push("confirmOrder")
        },
        removeFromLocalStorage(uId, id, price, quantity) {
            const data = this.getItems.filter(filter => filter.id != id)
            console.log("hello")
            console.log(this.getItems)
            this.getItems = data;
            this.reduceTotal(price, quantity)
            localStorage.setItem('cartItem', JSON.stringify(this.getItems))

        },
        reduceTotal(price, quantity) {
            console.log("cal")
            const totalSubtract = price * quantity
            this.totalPrice = this.totalPrice - totalSubtract
            this.calculateTotalPrice();
        },
        calculateTotal() {

            for (const item in this.getItems) {
                const number = +this.getItems[item].Price * this.getItems[item].quantity

                this.totalPrice += number
            }
            return this.totalPrice;
        },
        calculateGST() {

            this.gst = (this.totalPrice * 0.1);
            return this.gst;
        },

        calculateTotalPrice() {

            this.totalPricewithTax = 0;
            this.totalPricewithTax += this.calculateTotal() + this.calculateGST()



        }
    },

    mounted() {
        const items = JSON.parse(localStorage.getItem("cartItem"))
        //   console.log(items)
        this.getItems = items
        this.calculateTotalPrice();
        console.log(this.getItems)
    }

}
</script>

<style scoped>
#checkOutDiv {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    /* height: 1000px; */
    background-color: rgb(255, 255, 255);
}

#seeItem {
    background-color: white;
    width: 60%;
    height: 500px;
    overflow-x: hidden;
    overflow-y: scroll;
    text-align: center;
    margin: 5% 0px;
    padding: 10px;
    background-color: rgb(221, 221, 221);
}

#totalPrice {
    width: 30%;
}

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

#confirmOrderBtn {
    background-color: rgb(22, 22, 115);
    color: white;
    border: none;
    padding: 15px 10px;
    width: 70%;
    font-size: 17px;
}

#confirmOrderBtn:hover {
    cursor: pointer;
}

#totalPrice {
    font-size: 20px;
}


/* width */
::-webkit-scrollbar {
    width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
    background: #ffffff;
}

/* Handle */
::-webkit-scrollbar-thumb {
    background: #888;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
    background: #555;
}
</style>