CREATE DATABASE crm_database
/* then we go to the crm_database \c crm_database */
CREATE TABLE leads (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(30),
    phone VARCHAR(30),
    source VARCHAR(30),
    company VARCHAR(30),
    status VARCHAR(20) NOT NULL DEFAULT 'New',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE opportunities ( 
    id BIGSERIAL PRIMARY KEY, 
    account_name VARCHAR(255) NOT NULL, 
    amount INTEGER NOT NULL, 
    close_date DATE, 
    recent_activity VARCHAR(500), 
    stage VARCHAR(50) NOT NULL, 
    status VARCHAR(50) NOT NULL 
);