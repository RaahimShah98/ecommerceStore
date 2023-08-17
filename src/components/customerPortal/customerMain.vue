<template>
    <div id="sideBarBtn">
        <button class="w3-button w3-teal w3-medium" v-on:click="w3_open()" id="openSideBarBtn">☰</button>
        <button v-on:click="w3_close()" class="w3-button w3-teal w3-xlarge" id="closeSideBarBtn"
            style="display:none">&times;</button>

    </div>

    <div id="customerMainPage">
        <customerSideBar id="mySidebar" style="display:none"></customerSideBar>


        <div id="customerMainBody">
            <div id="customerSliderComponent">
                <customerSlider></customerSlider>
            </div>
            <customerBody></customerBody>
            <!-- hello -->
        </div>


    </div>
</template>

<script>

import customerSideBar from './customerSideBar.vue'
import customerSlider from './customerSlider.vue';
import customerBody from './customerBody.vue';

export default {
    data() {
        return {
            dispalyOn: true
        }
    },
    methods: {
        w3_open() {
            document.getElementById("mySidebar").style.display = "block";
            document.getElementById("openSideBarBtn").style.display = "none";
            document.getElementById("closeSideBarBtn").style.display = "block";

        },

        w3_close() {
            document.getElementById("mySidebar").style.display = "none";
            document.getElementById("openSideBarBtn").style.display = "block";
            document.getElementById("closeSideBarBtn").style.display = "none";
        }
    },
    components: {
        customerSideBar,
        customerSlider,
        customerBody,

    }
}
</script>

<style scoped>
#customerMainPage {
    display: flex;
    height: 100%;

    background-color: rgb(255, 255, 255);
}

#customerMainBody {
    display: inline-block;
    width: 100%;
    height: 100%;
    padding: 10px;
    background-color: rgb(255, 255, 255);
    border-left: 1px solid black;
}

#mySidebar {
width: 20%;
}

#sideBarBtn {
    display: flex;
}

#customerSliderComponent {
    height: 60%;
}</style>