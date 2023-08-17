
<template>
    <div class="customernNavBar">

        <logo></logo>


        <div id="custNavBarSearchField">
            <form class="searchFieldForm">Search <input type="search" placeholder="searchItems">


            </form>

        </div>

        <loginButton></loginButton>

        <shoppingCart></shoppingCart>

    </div>
</template>

<script>

import loginButton from '../generalComponents/loginButton.vue'
import logo from '../generalComponents/storeLogo.vue'
import shoppingCart from '../generalComponents/cartComponent.vue'
export default {
    components: {
        loginButton,
        logo,
        shoppingCart
    },


}
</script>

<style scoped>
/* .customernNavBar */
.customernNavBar {
    display: flex;
    justify-content: space-between;
    padding: 20px 0px;
    border: 1px solid black;
    padding-left: 10px;
}

#custNavBarLogo {
    text-align: center;
    width: 10%;
}

#custNavBarSearchField {
    text-align: center;
    width: 70%;
}


.searchFieldForm input {
    padding: 10px;
    font-size: small;
    
    width: 50%;
    border: none;
    border: 1px solid black;
}

.navBarBtn:hover {
    cursor: pointer;
    background-color: rgb(198, 128, 0);

}
</style>