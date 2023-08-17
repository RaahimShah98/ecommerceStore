<template>

        <customerHeader></customerHeader>
        <customerMain></customerMain>
        <customerFooter></customerFooter>
</template>

<script>

import customerHeader from './customerHeader.vue';

import customerMain from './customerMain.vue';
import customerFooter from './customerFooter.vue';

    export default {
        components: {
            customerHeader,
            customerMain,
            customerFooter
          
        },
        created(){
            localStorage.removeItem("categoryId")
        }
    }
</script>

<style lang="scss" scoped>

</style>