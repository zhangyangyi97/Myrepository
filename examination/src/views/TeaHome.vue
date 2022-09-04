<template>
    <div>
        <el-container>
            <el-aside width="200px">
                <TeaAsideMenu :style="menuHeight"></TeaAsideMenu>
            </el-aside>

            <el-container>
                <el-header>
                    <TeaHeadMenu></TeaHeadMenu>
                </el-header>
                <el-main>
                    <router-view  id="main"></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import TeaAsideMenu from "@/components/teacher/TeaAsideMenu";
    import TeaHeadMenu from "@/components/teacher/TeaHeadMenu";
    export default {
        name: "TeaHome",
        components: {TeaHeadMenu, TeaAsideMenu},
        provide(){
            return{
                reload:this.reload
            }
        },
        data(){
          return{
              collapse:'',
              menuHeight: {
                  height: "",
              },
          }
        },
        mounted() {
            this.$router.replace("/teaHome/teaFirstPage")
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },



        },

    }
</script>

<style scoped>

</style>