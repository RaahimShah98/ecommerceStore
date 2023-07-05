
<template>
    <div id="addItemDiv">
        <form action="">
            <input type="text" name="" id="addItemName" class="addItemField" placeholder="Name" v-model="Name">
            <input type="text" name="" id="addItemName" class="addItemField" placeholder="Price" v-model="Price">
            <input type="text" name="" id="addItemName" class="addItemField" placeholder="Description"
                v-model="Description">
            <input type="text" name="" id="addItemName" class="addItemField" placeholder="Quantity" v-model="Quantity">
            <br>
            <select name="" id="" class="addItemField" v-model="Category">
                <option value="">option1</option>
                <option value="">option2</option>
                <option value="">option3</option>
                <option value="">option4</option>
                <option value="">option5</option>
                <option value="">option6</option>

            </select>
            <input type="file" class="addItemField" name="" id="" accept="image/*">
            <button id="addItemBtn" onclick="addItem">Add Item</button>


        </form>

    </div>
</template>

<script>

export default {
    data() {
        return {
            Name: null,
            Price: null,
            Description: null,
            Quantity: null,
            image: null,
            Category: null,
            imgSrc: null
        }
    },
    methods: {
        addItem(){
            this.setImgURL();

            

        },
        setImgURL(event) {
            const file = event.target.files[0];
            const reader = new FileReader();
            console.log(reader);
            reader.onload = () => {
                this.imgSrc = reader.result;
            };

            reader.readAsDataURL(file);
        }
    }
}

</script>

<style scoped>
.addItemField {
    margin: 5px;
    width: 60%;
    padding: 5px;

}
</style>