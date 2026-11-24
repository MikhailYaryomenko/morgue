CREATE UNIQUE INDEX uk_deceased_active_id
ON deceased (deceased_id)
WHERE deletion_requested = FALSE;