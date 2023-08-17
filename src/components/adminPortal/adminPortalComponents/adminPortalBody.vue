<template>
    <div id="adminPortalBody">
            
        <adminNavBar></adminNavBar>

        <div id="adminPortalBodyRight">
         

        </div>

    </div>
</template>


<script>
import adminNavBar from './adminNavBar.vue';
// import axios from 'axios';

export default {
    components:{
        adminNavBar,
    }
}

</script>

<style>

#adminPortalBody {

    background-color: rgb(255, 255, 255);
    padding: 10px;
    display: flex;
    justify-content: space-between;
  

}



#adminPortalBodyRight {
    display: block;
    background-color: rgb(255, 255, 255);
    padding: 10px;
    border-radius: 3px;
    width:75%;
    height: 800px;
    overflow-y: scroll;
    margin-left: 2px;
    text-align: center;
    scroll-behavior: smooth;
}


#adminPortalDetailHeader {
    font-size: 20px;
}
</style>