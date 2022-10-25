import axios from 'axios';

export const api = axios.create({
  baseURL: 'https://ebutler.azurewebsites.net',
});
