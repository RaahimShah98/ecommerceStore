<template>
    <div id="customerSideBar">

        <ul class="categoryList">
            <li :id="cat.id" class="categoryName" v-for="cat in categories" :key="cat.id"
                v-on:click="changeCategory(cat.name, cat.id)">{{ cat.name }}</li>
        </ul>
    </div>
</template>

<script>
import axios from 'axios';


export default {
    data() {
        return {
            categories: {}
        }
    },
    methods: {
        changeCategory(name, id) {
            console.log(name)
            console.log(id)
       
            const field = document.getElementById(`${id}`)
            field.style.backgroundColor = "orange";
            field.style.color = "white"
          
            // console.log(field)
            // console.log(name)
            localStorage.setItem("categoryId", id)

            if (this.$route.path === `/viewItemByCategory/${id}`) {
                // If it's the same, use this.$router.replace to navigate without adding to history
                console.log("here")
                this.$router.replace(`/viewItemByCategory/${id}`);
            
                location.reload();

            } else if(this.$route.path != `/viewItemByCategory/${id}`){
                // If it's different, use this.$router.push to navigate and add to history
                console.log("there")
                this.$router.push(`/viewItemByCategory/${id}`);
                window.location.replace('/viewItemByCategory/${id}');
            }

            // location.reload();
        }
    },
    mounted() {
        axios.get("http://localhost:8080/manageCategory/viewCategory").then(response => {
            // console.log(response);
            this.categories = response.data;
            // console.log(this.categories)
        }).catch(error => {
            console.log(error)
        })
    }
}
</script>

<style scoped>
#customerSideBar {
    text-align: center;
    width: 20%;
}

.categoryList {
    width: 100%;
    text-align: center;
}

.categoryName {
    padding: 10px 0px;
    background-color: white;
    text-align: center;
    width: 100%;
}

.categoryName:hover {
    cursor: pointer;
    background-color: orange;
    color: white;
}
</style>