<template>
    <div id="confirmOrderPage">
        <div id="customerInformationField">
            <form>

                <ul>
                    <li>
                        <p>Email: </p><input type="email" placeholder="email" v-model="email" required=true>
                    </li>

                    <li>
                        <p>FirstName: </p><input type="text" placeholder="First Name" v-model="firstName" required="">
                    </li>
                    <li>
                        <p>LastName: </p><input type="text" placeholder="Last Name" v-model="lastName" required="">
                    </li>
                    <li>
                        <p>PhoneNo: </p><input type="text" placeholder="PhoneNo" v-model="phoneNo" required="">
                    </li>
                    <li>
                        <p>Address: </p><input type="text" placeholder="Address" v-model="Address" required="">
                    </li>
                    <li>
                        <p>City </p><input type="text" placeholder="Address" v-model="city" required="">
                    </li>

                </ul>
                <button v-on:click="confirmOrder">confirmOrder</button>

            </form>
        </div>

        <div id="OrderDetails">
            <div class="viewitems">
                <viewItems :getItems= order.orderItems></viewItems>
            </div>
        </div>

    </div>
</template>

<script>
import axios from 'axios'
import viewItems from '../generalComponents/viewItemCard.vue'
import { v4 as uuidv4 } from 'uuid';

export default {
    data() {
        return {
            "email": null,
            "firstName": null,
            "lastName": null,
            "phoneNo": null,
            "Address": null,
            "city": null,

            order: {
                orderid: null,
                orderItems: [],
                orderDetail: null,
                customerDetail: null

            }
        }


    }, components: {
        viewItems
    },
    methods: {
        setCustomerDetail() {
            let customerDetails = {
                email: this.email,
                firstName: this.firstName,
                lastName: this.lastName,
                phoneNo: this.phoneNo,
                Address: this.Address,
                city:this.city
            }

            return customerDetails;
        },
        setOrderDetails() {
            let orderDetail = {
                email: this.email,
                phone: this.phoneNo
            }
            return orderDetail
        },
        confirmOrder() {
            event.preventDefault();
            // console.log(this.email)
            this.order.orderid =  uuidv4();

            this.order.orderDetail = this.setOrderDetails()
            this.order.customerDetail = this.setCustomerDetail()
      

            console.log(this.order)
            console.log(this.order.orderItems)
            axios.post("http://localhost:8080/manageOrder/addOrder", this.order).then(response=>{

                console.log(response)

            }).catch(error=>{
                alert("Wrong Datda Entered")
                console.log(error)

            })

        }

    },
    created() {

        this.order.orderItems = JSON.parse(localStorage.getItem("cartItem"))
        // console.log(this.orderDetails)

    }


}
</script>

<style scoped>
#confirmOrderPage {
    display: flex;
    justify-content: space-between;

}

#customerInformationField {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 60%;
    background-color: rgb(255, 255, 255);
}

#customerInformationField form {
    background-color: rgb(91, 86, 86);
    color: white;
    width: 80%;
    height: 100%;
}

#customerInformationField input {
    padding: 10px;
    width: 50%;
}

#customerInformationField li {
    display: flex;
    margin: 20px 0px;
    justify-content: flex-start;
    /* justify-content: space-evenly; */
    width: 100%;
}

#customerInformationField p {

    /* justify-content: space-evenly; */
    width: 100px;
}

#OrderDetails {
    width: 43%;
    background-color: white;
    background-color: grey;
}

.viewItems {
    height: 100px;
    width: 20px;
}
</style>