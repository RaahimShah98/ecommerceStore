<template>
    <adminHeader></adminHeader>

    <div id="adminPortalBody">
        <navBar></navBar>
        <div id="adminPortalBodyRight">

            <table id="categoryTable">
                <tr class="viewTableRow">
                    <td class="viewTableData"><b>Order ID</b></td>
                    <td class="viewTableData"><b>Product ID</b></td>
                    <td class="viewTableData"><b>Quantity</b></td>
                    
                   
                </tr>
                
                <tr class="viewTableRow" v-for="ord in order" :key="ord.id">

                    <td class="viewTableData">{{ ord.orderId }}</td>
                    <!-- <td class="viewTableData">{{ ord.name }}</td> -->
                    <td class="viewTableData">{{ ord.productId }}</td>
                    <!-- <td class="viewTableData">{{ ord.phoneNo }}</td> -->
                    <td class="viewTableData">{{ ord.productQuantity }}</td>
                </tr>

            </table>
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
            order: {},
           
        }
    },methods:{
        viewOrder(custID){
            console.log(custID)

        }
    },
    created() {
        console.log("hello")
       const orderId =  localStorage.getItem("orderId")

       console.log(typeof(orderId))
        axios.post("http://localhost:8080/manageOrder/viewOrdersById" , orderId)
            .then(response => {
           
                // console.log(response.data)
            //    console.log(response.data)
                this.order = response.data;
                
                console.log(this.order)

            })
            .catch(error => {
                // Handle the error
                console.error(error);
            });
    }
}

</script>

<style scoped>
#categoryTable{
    width: 100%;
}

.adminRemoveItemBTN{
    background-color: rgb(0, 132, 255);
}
</style>