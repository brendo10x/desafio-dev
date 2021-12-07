ARG PROFILE_ACTIVE_CLIENT

# Build stage
FROM node:14 as build-stage
COPY ./ /usr/src/app
WORKDIR /usr/src/app
RUN npm install
RUN npm run ng build --configuration=${PROFILE_ACTIVE_CLIENT}

# Compiled app based on nginx
FROM nginx:1.19
COPY --from=build-stage /usr/src/app/dist/app-desafio-bycoders /usr/share/nginx/html
COPY /nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 1827