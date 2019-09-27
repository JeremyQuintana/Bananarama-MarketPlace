# base image
FROM node:12.2.0-alpine AS builder

# set working directory
WORKDIR /app
COPY . .

# add `/app/node_modules/.bin` to $PATH
#ENV PATH /app/node_modules/.bin:$PATH

# install and cache app dependencies
#COPY package.json /app/package.json
RUN npm install --silent
#RUN npm run build --prod
#RUN npm install -g serve
RUN yarn run build
EXPOSE 3000

# start app
#CMD ["npm", "start"]
FROM node:12.2.0-alpine
RUN yarn global add serve
WORKDIR /app
COPY --from=builder /app/build .
CMD ["serve", "-p", "3000", "-s", "."]