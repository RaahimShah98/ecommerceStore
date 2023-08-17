<template>
    <adminHeader></adminHeader>

    <div id="adminPortalBody">
        <navBar></navBar>
        <div id="adminPortalBodyRight">

            <table id="categoryTable">
                <tr class="viewTableRow">
                    <td class="viewTableData">Order ID</td>
                    <td class="viewTableData">User email</td>
                    <td class="viewTableData"><b>Status</b></td>
                    <td class="viewTableData"></td>
                    <td class="viewTableData"></td>
                    <td class="viewTableData"></td>
                </tr>

                <tr class="viewTableRow" v-for="ord in order" :key="ord.id">

                    <td class="viewTableData">{{ ord.id }}</td>
                    <!-- <td class="viewTableData">{{ ord.name }}</td> -->
                    <td class="viewTableData">{{ ord.email }}</td>
                    <!-- <td class="viewTableData">{{ ord.phoneNo }}</td> -->
                    <td class="viewTableData">{{ ord.status }}</td>
                    <td class="viewTableData"><button class="adminRemoveItemBTN" id="completeOrderBtn" v-on:click="confirm(ord.id)" >Complete Order</button></td>
                    <td class="viewTableData"><button class="adminRemoveItemBTN" id="cancelOrderBtn" v-on:click="cancel(ord.id)">Cancel Order</button></td>
                    <td class="viewTableData"><button class="adminRemoveItemBTN" v-on:click="viewOrder(ord.id)">View Details</button></td>
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
        viewOrder(orderId){
            this.$router.push("/adminPortal/viewOrderDetails/" + orderId)
            localStorage.setItem("orderId", orderId)

        },
        confirm(orderId){
            axios.post("http://localhost:8080/manageOrder/confirmOrder" , orderId)
            .then(response => {
           
                console.log(response)

            })
            .catch(error => {
                // Handle the error
                console.error(error);
            });
        },
        cancel(orderId){
            axios.post("http://localhost:8080/manageOrder/cancelOrder" , orderId)
            .then(response => {
           
                console.log(response)

            })
            .catch(error => {
                // Handle the error
                console.error(error);
            });
        }
    },
    created() {

        axios.get("http://localhost:8080/manageOrder/viewOrders")
            .then(response => {
           
                // console.log(response.data)
            //    console.log(response.data)
                this.order = response.data;

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

#completeOrderBtn{
    background-color: rgb(20, 184, 20);
}
#completeOrderBtn:hover{
    background-color: rgb(13, 126, 13);
}

#cancelOrderBtn{
    background-color: rgb(255, 29, 29);
}

#cancelOrderBtn:hover{
    background-color: rgb(193, 23, 23);
}
</style>