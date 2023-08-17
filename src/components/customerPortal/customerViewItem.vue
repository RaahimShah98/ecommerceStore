<template>
    <customerHeader></customerHeader>
    <div id="viewItemDiv">
        <div id="ItemImage">
            <img class="itemImageDisplay" :src="viewItem.image" alt="">
        </div>
        <div id="itemDetails">
            <h1>{{ viewItem.Name }}</h1>
            <!-- <p>{{ viewItem.description }}</p> -->
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Numquam, odit nobis tempora doloribus consequuntur
                praesentium vel temporibus porro id quaerat optio vero iusto beatae debitis autem natus aliquid qui hic
                ducimus in eaque reiciendis. Alias id, illum, facilis, porro nobis sunt quidem impedit numquam harum officia
                quas consectetur commodi temporibus necessitatibus facere corporis hic tempora vel! Nemo et, dolor odio
                aliquam aliquid perspiciatis magni officia eos esse commodi veritatis. Molestias unde cumque voluptas! Saepe
                dicta veniam, facere odit ab esse nostrum! Rem, exercitationem. Expedita eos sequi tenetur quisquam
                exercitationem voluptatibus praesentium saepe facere assumenda. Quod maiores expedita error dolor porro
                numquam, omnis laudantium rem labore unde, cupiditate quibusdam ab voluptas? Id accusantium officia sapiente
                impedit natus recusandae qui quo quidem esse sit consequatur fugiat odit, perferendis illo. Debitis, unde
                laudantium. Itaque eveniet magnam explicabo libero quaerat beatae quisquam dolore sunt dicta at odio
                excepturi repudiandae corporis asperiores, aliquid a deserunt et eos laboriosam officiis tempora.</p>
            <b>Quantity:</b> <input type="number" v-model="quantity" class="itemQuantity">

            <div class="addToCartBtnDiv">
                <button class="addToCartBtn" :id="viewItem.id"
                    v-on:click="addToCart(viewItem.id, viewItem.Name, viewItem.Price, viewItem.image, quantity)">Add To
                    Cart</button>
            </div>
        </div>
    </div>
    <customerFooter></customerFooter>
</template>

<script>

import customerHeader from './customerHeader.vue';

import customerFooter from './customerFooter.vue';

export default {
    data() {
        return {
            items: {},
            cartItems: [],
            viewItem: [],
            quantity: 1

        }
    },
    components: {
        customerHeader,
        customerFooter

    }, methods: {
        addToCart(itemId, itemName, itemPrice, itemImage, itemQuantity) {
       

            const itemToCart = {id: itemId, Name: itemName, Price: itemPrice, image: itemImage, quantity: itemQuantity }

            if (localStorage.cartItem) {
                const cartItem = JSON.parse(localStorage.getItem("cartItem"))
                console.log(cartItem)

                let quantity = itemQuantity;
                let index = cartItem.findIndex(item => item.id === itemId);
                if (index > -1) {
                    cartItem[index].quantity += quantity;
                    
                    localStorage.setItem('cartItem', JSON.stringify(cartItem))

                } else {
                    cartItem.push(itemToCart)
                    localStorage.setItem('cartItem', JSON.stringify(cartItem))
                }
            }else{
                    this.cartItems.push(itemToCart)
                    localStorage.setItem('cartItem', JSON.stringify(this.cartItems))
                
            }




        },
        fetchCartData() {
            const existingCartData = JSON.parse(localStorage.getItem('cartItem'));
            // console.log(existingCartData)
            if (existingCartData && Array.isArray(existingCartData)) {
                this.cartItems = existingCartData;
                // console.log("called")
            }
        },

        fetchItemData() {

            const existingCartData = JSON.parse(localStorage.getItem('viewItem'));


            this.viewItem = existingCartData;
        
        }
    },

    mounted() {
        this.fetchItemData();
        this.fetchCartData();
    }, unmounted() {
        // localStorage.clear();
    }


}
</script>

<style scoped>
#viewItemDiv {
    display: flex;
    justify-content: center;
    padding: 10px 20px;
}

#ItemImage {
    width: 700px;
    height: 400px;
}

#itemDetails {
    width: 60%;
}

.itemImageDisplay {
    width: 70%;
    height: 100%;
}

.addToCartBtn {
    display: inline;
    text-align: center;
    background-color: rgb(0, 0, 113);
    color: white;
    border: none;
    font-size: 20px;
    width: 50%;
    padding: 10px;
    border-radius: 8px;
}

.addToCartBtn:hover {
    cursor: pointer;
}

.addToCartBtnDiv {
    display: block;
    text-align: center;
}

.quantitySelectTab {
    width: 100px;
    margin: 20px 0px;

}
</style>