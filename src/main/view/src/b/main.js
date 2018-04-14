/**
 * Created by sinrin on 2018/4/14.
 */
import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'

Vue.config.productionTip = false

new Vue({
    data(){
        return {
            msg: ''
        }
    },
    mounted(){
        axios.get('/msg').then(({data}) => {
            this.msg = data;
        })
    },
    render: h => h(App)
}).$mount('#app')