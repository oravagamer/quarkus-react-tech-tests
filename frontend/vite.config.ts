import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: "0.0.0.0",
    port: 3000,
    proxy: {
      '/rest/api': {
        target: 'http://127.0.0.1:8081',
        changeOrigin: true,
        secure: false,
        ws: true
      },
      '/kc': {
        target: 'http://127.0.0.1:8080',
        rewrite: path => path.replace(/^\/kc/, '/auth'),
        changeOrigin: true,
        secure: false,
        xfwd: true,
        ws: true
      }
    }
  }
})
