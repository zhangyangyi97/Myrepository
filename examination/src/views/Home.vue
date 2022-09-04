<template>
    <div>
        <TopMenu></TopMenu>
        <router-view></router-view>
    </div>
</template>

<script>
    import TopMenu from "@/components/menu/TopMenu";
    export default {
        name: "Home",
        components: {TopMenu},
        mounted() {
            this.$router.replace("/home/firstPage")
        }
    }
</script>

<style scoped>

</style>