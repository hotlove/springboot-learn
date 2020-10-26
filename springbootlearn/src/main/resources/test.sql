SELECT t.title FROM   title t WHERE  
    kind_id = 1 AND production_year >= 2005 AND NOT EXISTS (   SELECT  *   FROM  cast_info c   WHERE  c.movie_id = t.id   AND c.role_id = 8)