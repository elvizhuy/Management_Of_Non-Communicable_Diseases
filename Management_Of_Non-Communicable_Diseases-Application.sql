CREATE TABLE `facilities` (
                              `id` int PRIMARY KEY AUTO_INCREMENT,
                              `name` varchar(255),
                              `address` text,
                              `phone_number` varchar(255),
                              `email` varchar(255),
                              `deleted_at` datetime
);

CREATE TABLE `departments` (
                               `id` int PRIMARY KEY AUTO_INCREMENT,
                               `name` varchar(255),
                               `description` text,
                               `deleted_at` datetime
);

CREATE TABLE `department_facilities` (
                                         `id` int PRIMARY KEY AUTO_INCREMENT,
                                         `facility_id` int,
                                         `department_id` int
);

CREATE TABLE `accounts` (
                            `id` int PRIMARY KEY AUTO_INCREMENT,
                            `department_facility_id` int,
                            `user_name` varchar(255),
                            `password` varchar(255) COMMENT 'bcrypt',
                            `level` int,
                            `deleted_at` datetime
);

CREATE TABLE `specializations` (
                                   `id` int PRIMARY KEY AUTO_INCREMENT,
                                   `name` varchar(255)
);

CREATE TABLE `job_codes` (
                             `id` varchar(255) PRIMARY KEY,
                             `name` varchar(255),
                             `job_level` int,
                             `grade` int,
                             `coefficients_salary` float
);

CREATE TABLE `positions` (
                             `id` int PRIMARY KEY AUTO_INCREMENT,
                             `name` varchar(255),
                             `bonus` float
);

CREATE TABLE `staffs` (
                          `id` int PRIMARY KEY AUTO_INCREMENT,
                          `department_facilities_id` int,
                          `job_code` varchar(255),
                          `position_id` int,
                          `specialization_id` int,
                          `first_name` varchar(255),
                          `last_name` varchar(255),
                          `phone_number` varchar(255),
                          `email` varchar(255),
                          `id_number` varchar(255),
                          `base_salary` float,
                          `start_work` date,
                          `deleted_at` datetime
);

CREATE TABLE `diseases` (
                            `id` int PRIMARY KEY,
                            `name` varchar(255)
);

CREATE TABLE `medicine_groups` (
                                   `id` int PRIMARY KEY AUTO_INCREMENT,
                                   `name` varchar(255),
                                   `description` varchar(255),
                                   `diseases_id` int
);

CREATE TABLE `medicine_types` (
                                  `id` int PRIMARY KEY AUTO_INCREMENT,
                                  `name` varchar(255)
);

CREATE TABLE `medicines` (
                             `id` int PRIMARY KEY AUTO_INCREMENT,
                             `group_id` int,
                             `type_id` int,
                             `name` varchar(255),
                             `unit` varchar(255),
                             `description` text,
                             `instruction` text,
                             `deleted_at` datetime
);

CREATE TABLE `medicine_import_history` (
                                           `id` int PRIMARY KEY AUTO_INCREMENT,
                                           `facility_id` int,
                                           `total_quantity` int,
                                           `import_date` datetime,
                                           `deleted_at` datetime
);

CREATE TABLE `medicine_import_history_details` (
                                                   `id` int PRIMARY KEY AUTO_INCREMENT,
                                                   `import_history_id` int,
                                                   `medicine_id` int,
                                                   `manufacturer` varchar(255),
                                                   `manufacture_date` date,
                                                   `expiration_date` date,
                                                   `quantity` int,
                                                   `purchase_price` float,
                                                   `note` text,
                                                   `deleted_at` datetime
);

CREATE TABLE `medicine_export_history` (
                                           `id` int PRIMARY KEY AUTO_INCREMENT,
                                           `facility_id` int,
                                           `total_quantity` int,
                                           `export_date` datetime,
                                           `deleted_at` datetime
);

CREATE TABLE `medicine_export_history_details` (
                                                   `id` int PRIMARY KEY AUTO_INCREMENT,
                                                   `export_history_id` int,
                                                   `medicine_id` int,
                                                   `manufacturer` varchar(255),
                                                   `manufacture_date` date,
                                                   `expiration_date` date,
                                                   `quantity` int,
                                                   `selling_price` float,
                                                   `note` text,
                                                   `deleted_at` datetime
);

CREATE TABLE `medicine_inventories` (
                                        `id` int PRIMARY KEY AUTO_INCREMENT,
                                        `medicine_id` int,
                                        `facility_id` int,
                                        `total_quantity` int,
                                        `last_import_date` datetime,
                                        `last_export_date` datetime
);

CREATE TABLE `equpiment_types` (
                                   `id` int PRIMARY KEY AUTO_INCREMENT,
                                   `name` varchar(255)
);

CREATE TABLE `equpiments` (
                              `id` int PRIMARY KEY AUTO_INCREMENT,
                              `type` int,
                              `description` text,
                              `instruction` text,
                              `deleted_at` datetime
);

CREATE TABLE `equpiment_import_history` (
                                            `id` int PRIMARY KEY AUTO_INCREMENT,
                                            `facility_id` int,
                                            `total_quantity` int,
                                            `import_date` datetime,
                                            `deleted_at` datetime
);

CREATE TABLE `equpiment_import_history_details` (
                                                    `id` int PRIMARY KEY AUTO_INCREMENT,
                                                    `import_history_id` int,
                                                    `equpiment_id` int,
                                                    `manufacturer` varchar(255),
                                                    `manufacture_date` date,
                                                    `expiration_date` date,
                                                    `quantity` int,
                                                    `purchase_price` float,
                                                    `note` text,
                                                    `deleted_at` datetime
);

CREATE TABLE `equpiment_export_history` (
                                            `id` int PRIMARY KEY AUTO_INCREMENT,
                                            `facility_id` int,
                                            `total_quantity` int,
                                            `export_date` datetime,
                                            `deleted_at` datetime
);

CREATE TABLE `equpiment_export_history_details` (
                                                    `id` int PRIMARY KEY AUTO_INCREMENT,
                                                    `export_history_id` int,
                                                    `equpiment_id` int,
                                                    `manufacturer` varchar(255),
                                                    `manufacture_date` date,
                                                    `expiration_date` date,
                                                    `quantity` int,
                                                    `purchase_price` float,
                                                    `note` text,
                                                    `deleted_at` datetime
);

CREATE TABLE `equpiment_inventories` (
                                         `id` int PRIMARY KEY AUTO_INCREMENT,
                                         `equpiment_id` int,
                                         `facility_id` int,
                                         `total_quantity` int,
                                         `last_import_date` datetime,
                                         `last_export_date` datetime
);

CREATE TABLE `people` (
                          `id` int PRIMARY KEY AUTO_INCREMENT,
                          `id_number` varchar(255) ,
                          `insurance` varchar(255),
                          `name` varchar(255),
                          `date_of_birth` date,
                          `gender` ENUM ('female, male, other'),
                          `address` varchar(255),
                          `phone_number` varchar(255),
                          `email` varchar(255),
                          `note` text,
                          `deleted_at` datetime
);

CREATE TABLE `insurances` (
                              `insurance_id` varchar(255) PRIMARY KEY,
                              `register_place` varchar(255),
                              `start_date` date,
                              `expiration_date` date,
                              `deleted_at` datetime
);

CREATE TABLE `patients` (
                            `id` int PRIMARY KEY,
                            `people_id` varchar(255),
                            `disease_id` int,
                            `first_year_found` date,
                            `first_place_found` varchar(255)
);

CREATE TABLE `diagnosis` (
                             `id` int PRIMARY KEY,
                             `patient_id` int,
                             `facility_id` int,
                             `staff_id` int,
                             `systolic_blood_pressure` int,
                             `diastolic_blood_pressure` int,
                             `blood_glucose_index` int,
                             `symptoms` varchar(255),
                             `complications` varchar(255),
                             `result` varchar(255),
                             `date` date,
                             `deleted_at` datetime
);

CREATE TABLE `treatments` (
                              `id` int PRIMARY KEY,
                              `diagnosis_id` int,
                              `prescription_id` int,
                              `note` text,
                              `date` date,
                              `deleted_at` datetime
);

CREATE TABLE `medical_histories` (
                                     `id` int PRIMARY KEY,
                                     `patient_id` varchar(255),
                                     `diagnosis_id` int,
                                     `treatment` int,
                                     `date` date,
                                     `deleted_at` datetime
);

ALTER TABLE `department_facilities` ADD FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`);

ALTER TABLE `department_facilities` ADD FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`);

ALTER TABLE `accounts` ADD FOREIGN KEY (`department_facility_id`) REFERENCES `department_facilities` (`id`);

ALTER TABLE `staffs` ADD FOREIGN KEY (`department_facilities_id`) REFERENCES `department_facilities` (`id`);

ALTER TABLE `staffs` ADD FOREIGN KEY (`job_code`) REFERENCES `job_codes` (`id`);

ALTER TABLE `staffs` ADD FOREIGN KEY (`position_id`) REFERENCES `positions` (`id`);

ALTER TABLE `staffs` ADD FOREIGN KEY (`specialization_id`) REFERENCES `specializations` (`id`);

ALTER TABLE `medicine_groups` ADD FOREIGN KEY (`diseases_id`) REFERENCES `diseases` (`id`);

ALTER TABLE `medicines` ADD FOREIGN KEY (`group_id`) REFERENCES `medicine_groups` (`id`);

ALTER TABLE `medicines` ADD FOREIGN KEY (`type_id`) REFERENCES `medicine_types` (`id`);

ALTER TABLE `medicine_import_history` ADD FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`);

ALTER TABLE `medicine_import_history_details` ADD FOREIGN KEY (`import_history_id`) REFERENCES `medicine_import_history` (`id`);

ALTER TABLE `medicine_import_history_details` ADD FOREIGN KEY (`medicine_id`) REFERENCES `medicines` (`id`);

ALTER TABLE `medicine_export_history` ADD FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`);

ALTER TABLE `medicine_export_history_details` ADD FOREIGN KEY (`export_history_id`) REFERENCES `medicine_export_history` (`id`);

ALTER TABLE `medicine_export_history_details` ADD FOREIGN KEY (`medicine_id`) REFERENCES `medicines` (`id`);

ALTER TABLE `medicine_inventories` ADD FOREIGN KEY (`medicine_id`) REFERENCES `medicines` (`id`);

ALTER TABLE `medicine_inventories` ADD FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`);

ALTER TABLE `equpiments` ADD FOREIGN KEY (`type`) REFERENCES `equpiment_types` (`id`);

ALTER TABLE `equpiment_import_history` ADD FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`);

ALTER TABLE `equpiment_import_history_details` ADD FOREIGN KEY (`import_history_id`) REFERENCES `equpiment_import_history` (`id`);

ALTER TABLE `equpiment_import_history_details` ADD FOREIGN KEY (`equpiment_id`) REFERENCES `equpiments` (`id`);

ALTER TABLE `equpiment_export_history` ADD FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`);

ALTER TABLE `equpiment_export_history_details` ADD FOREIGN KEY (`export_history_id`) REFERENCES `equpiment_export_history` (`id`);

ALTER TABLE `equpiment_export_history_details` ADD FOREIGN KEY (`equpiment_id`) REFERENCES `equpiments` (`id`);

ALTER TABLE `equpiment_inventories` ADD FOREIGN KEY (`equpiment_id`) REFERENCES `medicines` (`id`);

ALTER TABLE `equpiment_inventories` ADD FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`);

ALTER TABLE `people` ADD FOREIGN KEY (`insurance`) REFERENCES `Insurances` (`insurance_id`);

