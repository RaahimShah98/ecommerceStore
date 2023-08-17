<template>
    <adminHeader></adminHeader>

    <div id="adminPortalBody">
        <navBar></navBar>
        <div id="adminPortalBodyRight">


            <div class="viewDiv" v-for="image in imgSlider" :key="image.id">
                <div id="ItemImageDiv">
                    <img class="itemImage" accept="image/*" :src="image.imgUrl">
                </div>

                <div id="ItemDetailDiv">

                    <p><b>ItemID:</b> {{ image.imgId }}</p>
                    <p><b>ItemName:</b> {{ image.imgName }}</p>
                    <button class="adminRemoveItemBTN" v-bind:id="image.imgId" v-on:click="deleteSlider(image.imgId)">Remove
                        Image</button>
                    <button class="adminEditItemBTN" v-bind:id="image.imgId"
                        v-on:click="editSlider(image.imgId, image.imgName , image.imgUrl)">Edit Slider</button>
                </div>
            </div>


        </div>

    </div>
</template>

<script>
import axios from 'axios'

import navBar from './adminNavBar.vue'
import adminHeader from './adminPortalHeader.vue'

export default {
    components: {
        navBar,
        adminHeader
    },
    data() {
        return {
            imgSlider: {}
        }
    }, methods: {
        deleteSlider(id) {

            // console.log(this.data.users.id);

            axios.post("http://localhost:8080/manageSliderImage/deleteSliderImage", id)
                .then(response => {
                    // Handle the response data
                    console.log(typeof (response.data))
                    console.log(response)
                    console.log("Data Sent")

                })
                .catch(error => {
                    // Handle the error
                    console.error(error);
                });
                
        },   editSlider(id , name , url) {
            console.log(id, name , url)
            this.$router.push({ path: "/adminPortal/addSliderImage", query: { sliderId: id, sliderName: name, sliderUrl:url , btnChange: "update" } })
        },
    },
    created() {
        console.log("hello")
        axios.get("http://localhost:8080/manageSliderImage/viewSliderImage")
            .then(response => {
                // Handle the response data

                this.imgSlider = response.data;
                console.log(this.imgSlider)

            })
            .catch(error => {
                // Handle the error
                console.error(error);
            });
    }

}
</script>

<style scoped>
.itemImage {
    width: 100%;
    height: 100%;
}

.viewDiv {
    display: flex;
    background-color: green;
    margin-bottom: 10px;
    width: 100%;
    border: 2px solid black;
    /* padding: 20px 0px; */
}

#ItemImageDiv {
    width: 30%;
    padding: 20px;

    background-color: grey;
    /* height: 100%; */
}

#ItemDetailDiv {
    width: 70%;
    display: inline-block;
    text-align: center;
    background-color: white;
    padding-top: 20px;
    padding-bottom: 20px;
}

#CustoemerDetailDiv p {
    margin-top: 10px;
    margin-bottom: 10px;
    font-size: 20px;
}

</style>