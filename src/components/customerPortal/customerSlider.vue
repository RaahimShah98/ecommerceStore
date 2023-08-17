

<template>
  <div class="slideshow-container">
    <div :id="slide.imgId" :key="slide.imgId" v-for="slide in slider" class="mySlides fade">
  
      <img class="sliderImage" :src="slide.imgUrl" />
      <div class="text">{{ slide.description }}</div>
    </div>
  </div>
  <br />
 
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      slider: [],
      slideIndex:0
    }
  },
  methods: {
    showSlides() {

        // this.slideIndex = index;
      let i
      let slides = document.getElementsByClassName('mySlides')

     

      for (i = 0; i < slides.length; i++) {
        slides[i].style.display = 'none'
      }
      this.slideIndex++
      if (this.slideIndex > slides.length) {
        this.slideIndex = 1
      }
    
    //   console.log(slides[0])
      slides[this.slideIndex - 1].style.display = 'block'
     
      setTimeout(this.showSlides, 2000) // Change image every 2 seconds
    },
    setSlideIndex(index){
        this.slideIndex = index;
    }
  },
  created() {
    // this.showSlides()
    setTimeout(this.showSlides, 500)
    axios.get('http://localhost:8080/manageSliderImage/viewSliderImage').then((response) => {
      this.slider = response.data
      console.log(this.slider)

    })
  }
}
</script>

<style>
* {
  box-sizing: border-box;
}
body {
  font-family: Verdana, sans-serif;
}
.slideshow-container {
  width: 100%; /* Set the container to span the entire width of the page */
  max-width: 1000px; /* Optional: Set a maximum width if needed */

  position: relative;
  margin: auto;
}

/* The slides */
.mySlides {
  display: none;
  width: 100%; /* Set the slide to span the entire width of the container */
}

/* The images inside slides */
.mySlides img {
  object-fit:fill;
  width: 100%; /* Set the image to span the entire width of the slide */
  height: 300px; /* Allow the height to adjust proportionally */
  margin-top: 20px;
}


/* Caption text */
.text {
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */
.dot {
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.active {
  background-color: #717171;
}

/* Fading animation */
.fade {
  animation-name: fade;
  animation-duration: 1.5s;
}

@keyframes fade {
  from {
    opacity: 0.4;
  }
  to {
    opacity: 1;
  }
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .text {
    font-size: 11px;
  }
}
</style>