import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'
import Particles from 'particles.vue3'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from "./request/request"; //引入request.js
const app = createApp(App)

//导入icon
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}


app.config.globalProperties.$axios = axios; //配置axios的全局引用
app.use(ElementPlus)
app.use(Particles)
app.use(store).use(router).mount('#app')

