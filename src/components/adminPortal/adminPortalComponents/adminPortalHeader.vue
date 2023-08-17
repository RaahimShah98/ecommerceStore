<template>
    <div id="header">

        <logo></logo>
        <button id="adminPortalLogOutButton">LogOut</button>

    </div>
</template>

<script>
import logo from '../../generalComponents/storeLogo.vue'
export default {
  components:{
    logo
  }
}

</script>

<style scoped>
#header {
    display: flex;
    justify-content: space-between;
    border: 1px solid black;
    padding: 10px 20px;
}



#adminPortalLogOutButton {
  display: inline-block;
  padding: 10px 20px;
  font-size: 20px;
  color: white;
  background-color: #f44336; /* Red color, you can change it to any other color you prefer */
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, opacity 0.3s;
}

#adminPortalLogOutButton:hover {
  background-color: #d32f2f; /* Darker shade of red on hover */
}

#adminPortalLogOutButton:active {
  background-color: #b71c1c; /* Even darker shade of red on active/clicked */
}
</style>
