# base image
FROM node:12.2.0-alpine AS builder




# install and cache app dependencies
COPY package.json /app/package.json
RUN cd /app; npm install --production
COPY . /app
WORKDIR /app
RUN yarn run build
EXPOSE 3000

# start app
FROM node:12.2.0-alpine
RUN yarn global add serve
COPY --from=builder /app/build .
CMD ["serve", "-p", "3000", "-s", "."]