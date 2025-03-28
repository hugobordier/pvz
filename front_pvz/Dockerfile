FROM oven/bun:1.2.5

WORKDIR /front_pvz

COPY . .

RUN bun install

EXPOSE 5173

CMD ["bun", "dev","--host"]
