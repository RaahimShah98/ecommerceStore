<template>
    
    <div id="custNavBarLogin">
          

          <div id="custNavBarLoginField">
              <button class="navBarBtn" v-on:click="toLogin">Login</button>
          </div>

      </div>

</template>

<script>
    export default {
        methods:{
        toLogin(){
            this.$router.push("/login")
        }
    }
    }
</script>

<style scoped>
#custNavBarLogin {
    display: flex;
    justify-content: center;
    width: 25%;
}

.navBarBtn{
    margin-right: 20px;
    padding: 10px 10px;
    background-color: orange;
    color: white;
    font-size: 15px;
    border: none;
    border-radius: 5px;
}


.navBarBtn:hover{
    cursor: pointer;
    background-color: rgb(198, 128, 0);
 
}
</style>