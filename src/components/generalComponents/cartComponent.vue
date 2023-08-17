<template>
    <div id="custNavBarCart">
        <div id="custNavBarAddToCart">
            <img class="shoppingCartImage" src="../../assets/icons8-shopping-cart-50.png" alt="" v-on:click="goToCart">

        </div>
    </div>
</template>

<script>
export default {
methods:{
    goToCart(){
        this.$router.push("/proceedToCheckOut")
    }
}
}
</script>

<style scoped>

#custNavBarCart{
    padding-right: 20px;
}
.shoppingCartImage {
    width: 35px;
    height: 30px;
}

.shoppingCartImage:hover{
    cursor: pointer;
}
</style>