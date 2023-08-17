<template>
    <div id="loginPage">
        <form id="loginForm" @submit.prevent="loginDetails()">
            <input type="email" name="" id="logInEmailField" class="addItemField" placeholder="Email"
                v-model="loginCredentials.email">
            <input type="password" name="" id="logInPasswordField" class="addItemField" placeholder="Password"
                v-model="loginCredentials.password">

            <button type="submit" id="addItemBtn">LogIn</button>
            
        </form>
    </div>
</template>

<script>
import axios from 'axios';


export default {
    data() {
        return {
            loginCredentials: {
                "email": "",
                "password": ""
            },
            responseData: {}
        }
    },
    methods: {

        loginDetails() {
            this.getStatus();
        },

        getStatus() {

            axios.get("http://localhost:8080/manageUsers/getUserStatus", {
                params: {
                    email: this.loginCredentials.email,
                    password: this.loginCredentials.password
                }
            }).then(response => {

                
                this.responseData = response
                if (this.responseData.data == "admin") {
                    console.log("pushed")
                    this.changeTable("/adminPortal/")
                }
                else if (this.responseData.data != "admin") {
                    alert(this.responseData.data)
                }
                console.log(this.responseData.data);
            }).catch(error => {
                console.log(error)
            })
        },

        changeTable(route) {
            this.$router.push(route)

        }
    },

}

</script>


<style scoped>
/* Styles for the form container */
#loginPage {
    display: flex;
    justify-content: center;
    background-color: grey;
    padding: 50px;

}

#loginForm {
    width: 27%;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    background-color: white;
    padding: 20px;
    border-radius: 5px;
}

input.addItemField {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
}

button {
  padding: 10px 20px;
  margin-right: 10px;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  margin: 10px;
  width: 70%;
}

/* Additional styles for the LogIn button */
#addItemBtn {
  background-color: #4caf50;
  color: white;
}

/* Additional styles for the Register button */
#registerBtn {
  background-color: #007bff;
  color: rgb(125, 125, 125);
}
</style>