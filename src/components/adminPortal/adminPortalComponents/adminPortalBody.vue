<template>
    <div id="adminPortalBody">
        <div id="adminPortalBodyLeft">

            <button class="adminNavButton" id="addItemBtn" >Add Items</button>
            <button class="adminNavButton" id="addItemBtn" >Check Inventory</button>
            <button class="adminNavButton" id="addItemBtn" >Add Category</button>
            <button class="adminNavButton" id="addItemBtn" >View Categories</button>
            <button class="adminNavButton" id="addItemBtn" >Check User</button>


        </div>

        <div id="adminPortalBodyRight">
            <adminAddItemComponent v-show=false></adminAddItemComponent>
            <viewItemComponent v-show=false></viewItemComponent>
            <adminAddCategoryComponent v-show=false></adminAddCategoryComponent>
            <viewCategoryComponent v-show=false></viewCategoryComponent>
            <viewCustomer v-show=true></viewCustomer>


        </div>

    </div>
</template>


<script>
import adminAddItemComponent from './adminAddItemComponent.vue';
import viewItemComponent from './viewItems.vue';
import adminAddCategoryComponent from './adminAddCategory.vue'; 
import viewCategoryComponent from './viewCategories.vue';
import viewCustomer from './viewCustomers.vue';



export default {
    data() {
        return {

        }
    },
    components: {
        adminAddItemComponent,
        viewItemComponent,
        adminAddCategoryComponent,
        viewCategoryComponent,
        viewCustomer



    }
}

</script>

<style scoped>
#adminPortalBody {

    background-color: grey;
    padding: 10px;
    display: flex;
    width: 97%;

}

#adminPortalBodyLeft {

    display: block;
    background-color: white;
    border-radius: 10px;
    width: 45%;
    margin-right: 10px;
    padding: 10px;
}

#adminPortalBodyRight {
    display: block;
    background-color: blue;
    padding: 10px;
    border-radius: 10px;
    width: 45%;
    text-align: center;
}

.adminNavButton {
    display: block;
    width: 100%;
    margin-bottom: 10px;
}

#adminPortalDetailHeader {
    font-size: 20px;
}
</style>