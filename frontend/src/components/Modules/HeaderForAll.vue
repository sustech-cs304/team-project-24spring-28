<template>
  <div class="header">
    <!-- Logo -->
    <div class="logo-section">
      <img src="../../assets/logo.png" alt="Company Logo" class="logo" />
      校园活动网
    </div>

    <!-- Navigation -->
    <div class="nav-section">
      <nav>
        <ul>
          <li v-for="item in menuItems" :key="item.id">
            <a :href="item.url">{{ item.label }}</a>
          </li>
        </ul>
      </nav>
    </div>

    <!-- User Profile -->
    <div class="user-profile-section">
      <div class="user-profile">
        <img :src="userAvatar" alt="User Avatar" class="avatar" />
        <span>{{ userName }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menuItems: [
        { id: 1, label: '主页', url: '/' },
        { id: 2, label: '帖子广场', url: '/square' },
        { id: 3, label: '活动管理', url: '/' },
      ],
      userAvatar: '../../assets/logo.png',
      userName: 'John Doe',
    };
  },
};
</script>

<style scoped>
/* Header Styles */
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  background-color: #ffcfdf;
}

/* Logo Styles */
.logo-section {
  display: flex;
  align-items: center;
}

.logo {
  width: 20px;
  height: auto;
}

/* Navigation Styles */
.nav-section {
  display: flex;
  align-items: center;
}

nav ul {
  list-style: none;
  display: flex;
}

nav li {
  margin-right: 20px;
}

nav a {
  text-decoration: none;
  color: #000;
}

/* User Profile Styles */
.user-profile-section {
  display: flex;
  align-items: center;
}

.user-profile {
  display: flex;
  align-items: center;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
}
</style>
