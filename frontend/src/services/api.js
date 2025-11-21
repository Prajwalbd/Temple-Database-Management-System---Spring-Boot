import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_API_URL || "http://localhost:8080/api";

export const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export const endpoints = {
  auth: {
    register: "/auth/register",
    login: "/auth/login",
  },
  members: "/members",
  staff: "/staff",
  halls: "/halls",
  hallBookings: "/hall-bookings",
  poojas: "/poojas",
  volunteers: "/volunteers",
};




