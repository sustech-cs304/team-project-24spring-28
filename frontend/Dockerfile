FROM node:18.17.1
LABEL authors="Shinomiya"

EXPOSE 8080

COPY ./ /usr/src/myapp/frontend
WORKDIR /usr/src/myapp/frontend

RUN rm Jenkinsfile && \
    rm Dockerfile && \
    npm install && \
    npm run serve
