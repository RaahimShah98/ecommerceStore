<template>
    <adminPortalHeader></adminPortalHeader>
    <adminPortalBody></adminPortalBody>

</template>


<script>

import adminPortalHeader from './adminPortalComponents/adminPortalHeader.vue';
import adminPortalBody from './adminPortalComponents/adminPortalBody.vue';

export default {
    
    components: {
        adminPortalHeader,
        adminPortalBody
    }
}


</script>

<style>

/* REMOVE BTN STYLING */
.adminRemoveItemBTN {
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  color: #fff;
  background-color: #f43636; /* Red color, you can change it to any other color you prefer */
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  margin: 15px;
  width: 70%;
}

.adminRemoveItemBTN:hover {
  background-color: #d3662f; /* Darker shade of red on hover */
  transform: translateY(-2px);
}

.adminRemoveItemBTN:active {
  background-color: rgb(240, 90, 90);  /* Even darker shade of red on active/clicked */
  transform: translateY(0);
}

/* Optional: Add a subtle animation on click */
.adminRemoveItemBTN:focus {
  outline: none;
  animation: pulse 0.6s;
}

@keyframes pulse {
  0% { transform: scale(1); }
  50% { transform: scale(1.05); }
  100% { transform: scale(1); }
}

/* TABLE DESIGN */
.viewTableRow{
    border: 1px solid black;

}
.viewTableData{
margin-right: 50px;
padding: 20px;
border: 1px solid black;
width: 20px;
height: 20px;

}

.adminEditItemBTN{
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  color: #fff;
  background-color: #4336f4; /* Red color, you can change it to any other color you prefer */
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  margin: 15px;
  width: 70%;
}

.adminEditItemBTN:hover {
  background-color: #2fd394; /* Darker shade of red on hover */
  transform: translateY(-2px);
}

.adminEditItemBTN:active {
  background-color: #000000; /* Even darker shade of red on active/clicked */
  color: white;
  transform: translateY(0);
}

</style>



