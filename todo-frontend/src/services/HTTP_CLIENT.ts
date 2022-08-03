import axios from 'axios';

//create axios instance
const axiosInstance = axios.create({
    baseURL: '/'
});

//set default header for Content-Type
axiosInstance.defaults.headers.common['Content-Type'] = 'application/json';

export default axiosInstance;
