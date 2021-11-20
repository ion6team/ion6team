import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { bootstrapVue, IconsPlugin } from 'bootstrap-vue'
import axios from 'axios'
import '@morioh/v-quill-editor/dist/editor.css';
import Editor from '@morioh/v-quill-editor'
import{
  ValidationObserver,
  ValidationProvider,
  extend,
  localize,
} from "vee-validate";
import * as rules from "vee-validate/dist/rules";


Vue.use(Editor);
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

Object.keys(rules).forEach(rule =>{
  extend(rule, rules[rule])
})

Vue.component("ValidationObserver", ValidationObserver)
Vue.component("ValidationProvider", ValidationProvider)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

