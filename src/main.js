import { createApp } from 'vue'
import App from './App.vue'
// import Vue from 'vue';
// import VueRouter from 'vue-router';

//Login PAGE 
import loginPage from "./components/LoginPage.vue"

//ADMIN PORTAL ROUTES
import adminPortal from "./components/adminPortal/adminPortal.vue";
// ADMIN PORTAL INNER ROUTES

import adminAddItemComponent from './components/adminPortal/adminPortalComponents/adminAddItemComponent.vue';
import viewItemComponent from './components/adminPortal/adminPortalComponents/viewItems.vue';
import adminAddCategoryComponent from './components/adminPortal/adminPortalComponents/adminAddCategory.vue' ;
import adminAddSliderImage from './components/adminPortal/adminPortalComponents/adminAddSliderImage.vue' ;
import adminAddUser from './components/adminPortal/adminPortalComponents/adminAddUser.vue'

//ADMIN VIEWS

import viewCategoryComponent from './components/adminPortal/adminPortalComponents/viewCategories.vue';
import viewCustomer from './components/adminPortal/adminPortalComponents/viewCustomers.vue';
import viewSliderImages from './components/adminPortal/adminPortalComponents/viewSliderImages.vue';
import viewCustomerData from './components/adminPortal/adminPortalComponents/adminViewCustomer.vue';
import viewOrder from './components/adminPortal/adminPortalComponents/viewOrder.vue';
import viewOrderDetail from './components/adminPortal/adminPortalComponents/viewOrderDetails.vue';

//ADMIN CHARTS

import orderStatChart from './components/adminPortal/adminPortalComponents/charts/orderStatChart.vue'
import customerStatChart from './components/adminPortal/adminPortalComponents/charts/customerStatChart.vue'

// import customerPortal from

import customerPortal from './components/customerPortal/customerPortal.vue'
import customerViewByCategory from './components/customerPortal/customerViewByCategory.vue'
import checkOutPage from './components/customerPortal/proceedToCheckOut.vue'


import customerViewItem from './components/customerPortal/customerViewItem.vue'
import customerConfirmOrder from './components/customerPortal/customerConfirmOrder.vue'

import { createRouter, createWebHistory } from 'vue-router'



customerConfirmOrder.vue
// Vue.use(VueRouter);

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/login' , component: loginPage },
        { path: '/adminPortal' , component: adminPortal },
        { path: '/adminPortal/addUser', component: adminAddUser},
        { path: '/adminPortal/AddItemComponent', component: adminAddItemComponent},
        { path: '/adminPortal/ViewItemComponent', component: viewItemComponent },
        { path: '/adminPortal/AddCategoryComponent', component: adminAddCategoryComponent },
        { path: '/adminPortal/viewCategoryComponent', component: viewCategoryComponent },
        { path: '/adminPortal/viewCustomer', component: viewCustomer },
        { path: '/adminPortal/viewSliderImage', component: viewSliderImages },
        { path: '/adminPortal/addSliderImage', component: adminAddSliderImage },
        { path: '/adminPortal/viewCustomerData', component: viewCustomerData },
        { path: '/adminPortal/viewOrder', component: viewOrder },
        { path: '/adminPortal/viewOrderDetails/:orderId', component: viewOrderDetail },
        { path: '/adminPortal/orderStatChart', component: orderStatChart },
        { path: '/adminPortal/customerStatChart', component: customerStatChart},

        //CUSTOMER ROUTES
        { path: '/' , component: customerPortal },
        { path: '/proceedToCheckOut' , component: checkOutPage },
        { path: '/viewItem/:itemId' , component: customerViewItem },
        { path: '/viewItemByCategory/:categoryID' , component: customerViewByCategory },
        { path: '/confirmOrder' , component: customerConfirmOrder },



       
    ]
});

const app = createApp(App)

app.use(router);

app.mount('#app');
