<template>
  <div id="adminPortalBodyLeft">


    <div class="adminNavDropdown">




      <button class="adminNavButton" v-on:click="toggleDropdown(0)">Categories</button>
      <ul>
        <div class="divItems" v-show="display[0]">
          <li>
            <button v-on:click="changeTable('AddCategoryComponent')" class="adminNavButtonIn">Add
              Category</button>
          </li>
          <li>
            <button v-on:click="changeTable('viewCategoryComponent')" class="adminNavButtonIn">View
              Categories</button>
          </li>
        </div>
      </ul>


      <button class="adminNavButton" v-on:click="toggleDropdown(1)">Users</button>
      <ul>
        <div class="divItems" v-show="display[1]">
          <li>
            <button v-on:click="changeTable('addUser', 'add')" class="adminNavButtonIn">Add User</button>
          </li>
          <li>
            <button v-on:click="changeTable('viewCustomer')" class="adminNavButtonIn">View User's</button>
          </li>
        </div>
      </ul>

      <button class="adminNavButton" v-on:click="toggleDropdown(2)">Slider</button>
      <ul>
        <div class="divItems" v-show="display[2]">
          <li>
            <button v-on:click="changeTable('addSliderImage')" class="adminNavButtonIn">Add Slider
              Image</button>
          </li>
          <li>
            <button v-on:click="changeTable('viewSliderImage')" class="adminNavButtonIn">View Slider
              Image</button>
          </li>
        </div>
      </ul>

      <button class="adminNavButton" v-on:click="toggleDropdown(3)">Products</button>
      <ul>
        <div class="divItems" v-show="display[3]">
          <li>
            <button v-on:click="changeTable('AddItemComponent')" class="adminNavButtonIn">Add Items</button>
          </li>
          <li>
            <button v-on:click="changeTable('viewItemComponent')" class="adminNavButtonIn" id="checkInventoryBtn">View
              Inventory</button>
          </li>
        </div>
      </ul>

      <button class="adminNavButton" v-on:click="toggleDropdown(4)">Customer Data</button>
      <ul>
        <div class="divItems" v-show="display[4]">
          <li>
            <button v-on:click="changeTable('viewCustomerData')" class="adminNavButtonIn">View Customers</button>
          </li>
        </div>
      </ul>

      <button class="adminNavButton" v-on:click="toggleDropdown(5)">Order Data</button>
      <ul>
        <div class="divItems" v-show="display[5]">
          <li>
            <button v-on:click="changeTable('viewOrder')" class="adminNavButtonIn">View Order</button>
          </li>
        </div>
      </ul>

      <button class="adminNavButton" v-on:click="toggleDropdown(6)">Statistics</button>
      <ul>
        <div class="divItems" v-show="display[6]">
          <li>
            <button v-on:click="changeTable('customerStatChart')" class="adminNavButtonIn">Product Stats</button>
          </li>
          <li>
            <button v-on:click="changeTable('orderStatChart')" class="adminNavButtonIn">Order Stats</button>
          </li>
        </div>
      </ul>

    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      isDropdownOpen: false,
      display: []
    };
  },
  methods: {
    changeTable(route, componentName) {
      this.$router.push("/adminPortal/" + route)

      this.$router.push({ path: "/adminPortal/" + route, query: { btnChange: componentName } })

      console.log(componentName)
    },

    toggleDropdown(index) {
      this.display[index] = !this.display[index]
      console.log(this.display[index])

    },
    openMenu(index) {
      let item = document.getElementsByClassName("divItems")
      console.log(item)
      this.display = !this.display
      for (let i = 0; i < item.length; i++) {
        if (i === index) {
          continue;
        }
        else {

          item[i].style.block = "none"

        }
      }
      item[index].style.display = "block"
    }
  },

}

</script>

<style scoped>
li {
  list-style: none;
}

#adminPortalBodyLeft {
  display: flex;
  justify-content: center;

  width: 30%;
  padding-top: 40px;
}

.adminNavButton {
  display: block;
  padding: 10px 20px;

  font-size: 18px;
  border: 1px solid #ccc;

  background-color: #f0f0f0;
  color: #333;
  width: 100%;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;
}

.adminNavDropdown {
  width: 100%;

}

.adminNavButtonIn {
  width: 100%;
  padding: 10px 5px;
  background-color: orange;
  color: white;
  border: none;
  border-bottom: 1px solid black;
}

.adminNavButtonIn:hover {
  cursor: pointer;
  background-color: rgb(150, 99, 3);

}

/* Hover effect */
.adminNavButton:hover {
  background-color: #f3c924;
  color: white;
}

/* Active state when button is clicked */
.adminNavButton:active {
  background-color: #aaa;
  color: #fff;
}

/* Hide li elements by default */
</style>