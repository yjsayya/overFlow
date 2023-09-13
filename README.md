jwt:
secret-key: stack_over_flow_clone_coding
# 30 days
token.expired-time-ms: 2592000000


spring:
datasource:
url: jdbc:mariadb://localhost:3306/overflow
username: root
password: xxxx
driver-class-name: org.mariadb.jdbc.Driver
jpa:
hibernate:
ddl-auto: update
show-sql: true