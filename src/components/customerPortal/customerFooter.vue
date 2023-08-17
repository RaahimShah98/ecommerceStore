<template>
    <div id="customerFooterArea">
        <div id="custFooterLogo">
            <logo></logo>

        </div>

        <div id="custFooterPolicy">
            <ul id="policyList">
                <li class ="policy">policy1</li>
                <li class ="policy">policy2</li>
                <li class ="policy">policy3</li>
                <li class ="policy">policy4</li>
                <li class ="policy">policy5</li>
                <li class ="policy">policy6</li>

            </ul>

        </div>

        <div id="custFooterLogo">
            <loginButton></loginButton>

        </div>
    </div>
</template>

<script>
import loginButton from '../generalComponents/loginButton.vue'
import logo from '../generalComponents/storeLogo.vue'

export default {
    components:{
        loginButton,
        logo
    }

}
</script>

<style scoped>
#customerFooterArea{
    display: flex;
    justify-content: space-between;
    padding: 20px 0px;
    background-color: rgb(110, 22, 22);
}

#custFooterLogo{
    width: 25%;
}

#custFooterPolicy{
    display: flex;
    justify-content: center;
    width: 50%;
    text-align: center;
}

#custFooterLogo{
    width: 25%;
}

#policyList{
    display: flex;
    flex-wrap: wrap;
    width: 50%;
    justify-content: center;
    color: white;
    
}

.policy{
    margin-top: 10px;
    margin-left: 20px;
    list-style: none;
}
</style>