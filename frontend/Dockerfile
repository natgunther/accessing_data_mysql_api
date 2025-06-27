# Step 1: Build the React app
FROM node:20-alpine as build

# Set working directory
WORKDIR /app

COPY package*.json ./
RUN npm install

# Copy everything into the container
COPY . .
RUN npm run build

# Step 2: Serve with Nginx
FROM nginx:stable-alpine

# Copy build output from previous stage to Nginx's public directory
COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
