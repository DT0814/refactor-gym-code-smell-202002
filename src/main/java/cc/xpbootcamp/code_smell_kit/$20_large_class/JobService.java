package cc.xpbootcamp.code_smell_kit.$20_large_class;

import java.util.List;

public class JobService {
    private JobRepository jobRepository;
    private JobLogRepository jobLogRepository;

    private JobService(JobRepository jobRepository, JobLogRepository jobLogRepository, LineRepository lineRepository) {
        this.jobLogRepository = jobLogRepository;
        this.jobRepository = jobRepository;
        this.lineRepository = lineRepository;
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public List<Job> getList(Long itemId) {
        return jobRepository.findAllByItemId(itemId);
    }

    public JobLog getLog(Long JobId) {
        return jobLogRepository.findByJobId(JobId);
    }
}
