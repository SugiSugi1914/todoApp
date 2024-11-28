DROP TABLE IF EXISTS tasks; --外部キー制約を考慮してtasksを先に削除
DROP TABLE IF EXISTS categories;



-- Categoryテーブル
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    category VARCHAR(255) NOT NULL UNIQUE
);

-- Taskテーブル
CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

-- サンプルデータ挿入
INSERT INTO categories (category) VALUES
('toDo'),
('作業中'),
('確認待ち'),
('完了');

INSERT INTO tasks (title, category_id) VALUES
('会議の準備', 1),
('家族と映画を見る', 2),
('食料品を買う', 3);
