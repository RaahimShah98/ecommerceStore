
<template>
    <adminHeader></adminHeader>

    <div id="adminPortalBody">
        <navBar></navBar>
        <div id="adminPortalBodyRight">

            <form>
                <input type="text" name="" id="addItemName" class="addItemField" placeholder="Id" v-model="sliderImage.id">
                <input type="text" name="" id="addItemName" class="addItemField" placeholder="Name"
                    v-model="sliderImage.Name">

                <input type="file" class="addItemField" name="" id="" @change="setImgURL">

                <button id="addItemBtn" v-on:click="addSliderImage" v-if="btnChange === 'add'">Add Slider</button>
    
                <button id="updateItemBtn" v-on:click="updateSlider" v-if="btnChange === 'update'">Update Slider</button>
            </form>

            <img  id = "SliderImagePreview" v-bind:src="sliderImage.imgSrc" >

        </div>
    </div>
</template>

<script>
import axios from 'axios';
// import { createCanvas, loadImage } from 'canvas';

import navBar from './adminNavBar.vue'
import adminHeader from './adminPortalHeader.vue'

export default {
    components: {
        navBar,
        adminHeader
    },
    data() {
        return {
            sliderImage: {
                "id": "",
                "Name": "",
                "imgSrc": ""
            },
            "btnChange": "add",
        }
    },
    methods: {
        addSliderImage() {
            event.preventDefault();
            console.log(this.sliderImage)

            axios.post("http://localhost:8080/manageSliderImage/addSliderImagewithId", this.sliderImage)
                .then(response => {
                    // Handle the response data

                    this.Categories = response.data;
                    // console.log(this.users.users[0]);
                    console.log(response.data);
                })
                .catch(error => {
                    // Handle the error
                    console.error(error);
                })
        },  updateSlider() {
            event.preventDefault();

            axios.post("http://localhost:8080/manageSliderImage/updateSliderImage", this.sliderImage)
                .then(response => {
                    // Handle the response data

                    console.log(response.data)

                })
                .catch(error => {
                    // Handle the error
                    console.error(error);
                })
        },
        setImgURL() {

            const file = event.target.files[0];

            if (file) {

                console.log("hello")
                const reader = new FileReader();

                reader.onload = () => {
                    const imgSrc = reader;
                    this.sliderImage.imgSrc = imgSrc.result;
                    console.log(this.sliderImage.imgSrc);
                    // Do something with the image source (e.g., assign it to a data property)
                 
                };

                reader.readAsDataURL(file);
            }

        }
    },
    beforeRouteEnter(to, from, next) {
            // Check if query parameters are present when entering the route
            
            if (Object.keys(to.query).length > 0) {
                // Set the dataVariable based on the query parameters

          
                next(vm => {
                    vm.sliderImage.id = to.query.sliderId;
                    vm.sliderImage.Name = to.query.sliderName;
                    vm.sliderImage.imgSrc = to.query.sliderUrl;
                    vm.btnChange = to.query.btnChange;

                });
            } else {
                next();
            }
        },
        beforeRouteUpdate(to, from, next) {
            // Check if query parameters are present when updating the route
            if (Object.keys(to.query).length > 0) {
                // Set the dataVariable based on the query parameters
                this.sliderImage.id = "";
                this.sliderImage.Name = "";
                this.sliderImage.imgSrc = "";
                this.btnChange = "add";
          


            }
            next();

        },
}

</script>

<style scoped>
.addItemField {
    margin: 5px;
    width: 60%;
    padding: 5px;

}
#SliderImagePreview{
    width: 100%;
    height: 60%;
}

#updateItemBtn {
    display: inline-block;
    padding: 12px 24px;
    font-size: 16px;
    color: #fff;
    background-color: #007bff;
    /* Blue color, you can change it to any other color you prefer */
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.2s;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

#addItemBtn {
    margin: 20px;
    display: inline-block;
    padding: 12px 24px;
    font-size: 16px;
    color: #fff;
    background-color: #168200;
    /* Blue color, you can change it to any other color you prefer */
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.2s;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

#addItemBtn:hover , #updateItemBtn {
    background-color: #0056b3;
    /* Darker shade of blue on hover */
    transform: translateY(-2px);
}
</style>