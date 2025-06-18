CREATE TABLE emerald_images (
    emerald_id BIGINT NOT NULL,
    image_url VARCHAR(255),
    CONSTRAINT fk_emerald FOREIGN KEY (emerald_id) REFERENCES emeralds(id) ON DELETE CASCADE
);
