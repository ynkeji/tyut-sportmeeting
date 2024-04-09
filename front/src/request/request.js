import axios from "axios";


const api = axios.create(
    {
        baseURL: "http://localhost:8899", //这里配置的是后端服务提供的接口
        timeout: 3000,
        headers:{
                "Content-Type":"application/json",
        }
    }
);





export default  api;
